class FriendshipsController < ApplicationController
  before_filter :restrict_access
  # GET /friendships
  # GET /friendships.json
  def index
    @friendships = Friendship.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @friendships }
    end
  end

  # GET /friendships/1
  # GET /friendships/1.json
  def show
    @user=current_user
    respond_to do |format|
      format.html # show.html.erb
      format.json { render json: @user.friends}
    end
  end

  # GET /friendships/new
  # GET /friendships/new.json
  def new
    @user=current_user
    friend=User.find_by_email(params[:email])
    #make sure the friend exists
    if(friend)
      #Check to see if the friendship already exists
      friendCheck=Friendship.find_by_user_id_and_friend_id(@user.id, friend.id)
      if(!friendCheck)
        #If there is no friendship between the two users, continue as normal
        @friendship = @user.friendships.build(:friend_id => friend.id)

        respond_to do |format|
          if @friendship.save
            @friendship=friend.friendships.build(:friend_id => @user.id)
            if @friendship.save
              UserMailer.confirmation_email(@user,friend).deliver
              format.html { redirect_to @friendship, notice: 'Friendship was successfully created.' }
              format.json { render json: {:created => 'true', :exists => 'true', :friends => 'false'}}
            else
              format.html { render action: "new" }
              format.json { render json: {:created => 'false', :friends => 'false', :exists => 'true'}}
            end
          else
            render json: {:created => 'false', :friends => 'false', :exists => 'true'}
          end
        end
      else
        #If the friendship exist, return this fact to the app. It will notify the user.
        render json: {:friends => 'true', :exists => 'true', :created => 'false'}
      end
    else
      #If the user does not exist, let the app know.
      render json: {:friends => 'false', :exists => 'false', :created => 'false'}
    end
    #@friendship = Friendship.new

    #respond_to do |format|
      #format.html # new.html.erb
      #format.json { render json: @friendship }
    #end
  end

  # GET /friendships/1/edit
  def edit
    @friendship = Friendship.find(params[:id])
  end

  # POST /friendships
  # POST /friendships.json
  def create
    @user=User.find(params[:uid])
    friend=User.find(params[:fid])
    #make sure the friend exists
    if(friend && @user)
      #Check to see if the friendship already exists
      friendShip=Friendship.find_by_user_id_and_friend_id(@user.id, friend.id)
      if(friendShip)
        #If there is no friendship between the two users, continue as normal
        friendShip.type='Confirmed'
        respond_to do |format|
          if @friendship.save
            friendShip=Friendship.find_by_user_id_and_friend_id(friend.id, @user.id)
            friendShip.type='Confirmed'
            if @friendship.save
              format.html { redirect_to @user, notice: 'Friendship was successfully created.' }
              format.json { render json: {:created => 'true', :exists => 'true', :friends => 'false'}}
            else
              format.html { redirect_to @user, notice: 'Something went wrong!'}
              format.json { render json: {:created => 'false', :friends => 'false', :exists => 'true'}}
            end
          else
            format.html { redirect_to @user, notice: 'Something went wrong!'}
            format.json {render json: {:friends => 'false', :exists => 'false', :created => 'false'}}
          end
        end
      else
        #If the friendship exist, return this fact to the app. It will notify the user.
        respond_to do |format|
          format.html { redirect_to @user, notice: 'Something went wrong! According to our records, this friendship was never requested!'}
          format.json {render json: {:friends => 'false', :exists => 'false', :created => 'false'}}
        end
      end
    else
      #If the user does not exist, let the app know.
      respond_to do |format|
        format.html { redirect_to @user, notice: 'Something went wrong! According to our records, you do not exist!'}
        format.json {render json: {:friends => 'false', :exists => 'false', :created => 'false'}}
      end
    end
  end

  # PUT /friendships/1
  # PUT /friendships/1.json
  def update
    @friendship = Friendship.find(params[:id])

    respond_to do |format|
      if @friendship.update_attributes(params[:friendship])
        format.html { redirect_to @friendship, notice: 'Friendship was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @friendship.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /friendships/1
  # DELETE /friendships/1.json
  def destroy
    @user=current_user
    friend=User.find_by_email(params[:email])
    if(friend)
      friendCheck=Friendship.find_by_user_id_and_friend_id(@user.id, friend.id)
      if(friendCheck)
        @friendship = @user.friendships.find_by_friend_id(friend.id)
        @friendship.destroy
        @friendship = friend.friendships.find_by_friend_id(@user.id)
        @friendship.destroy

        respond_to do |format|
          format.html { redirect_to root_url }
          format.json { render json: {:deleted => 'true'}}
        end
      else
        respond_to do |format|
          format.html { redirect_to root_url }
          format.json { render json: {:deleted => 'false'}}
        end
      end
    else
      respond_to do |format|
        format.html { redirect_to root_url }
        format.json { render json: {:deleted => 'false'}}
      end
    end
  end
end
