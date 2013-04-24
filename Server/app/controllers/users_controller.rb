class UsersController < ApplicationController
  # GET /users
  # GET /users.json

  #Lists the users. Was useful when debugging.
  def index
    @users = User.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @users }
    end
  end

  # GET /users/1
  # GET /users/1.json
  # Displays the user's page.
  # Or returns the user's lat/long and last message
  def show
    @user = User.find(session[:user_id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render json: {lat: @user.lat, long: @user.long, message: @user.message}}
    end
  end

  # GET /users/new
  # GET /users/new.json
  # Because we don't have a user-interface outside the app, we don't need
  # the new function.
  def new
    @user = User.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @user }
    end
  end

  # GET /users/1/edit
  def edit
    @user = User.find(params[:id])
  end

  # POST /users
  # POST /users.json
  def create
    @user = User.new(params[:user])
    #Attempt to save the new user, finilazing creation
    respond_to do |format|
      if @user.save
        #If user was successfully created, create an access token for them,
        #send them a welcom email, and return the needed information to the app.
        @user.create_api_key
        UserMailer.welcome_email(@user).deliver
        format.html { redirect_to @user, notice: 'User was successfully created.' }
        format.json { render json: {created: true, access_token: @user.api_key.access_token}} 
      else
        #If user was not successfully created, send back a JSON to the app
        #with the error message and the created flag set to false
        format.html { render action: "new" }
        format.json { render json: {created: false, error: @user.errors.full_messages}} 
      end
    end
  end

  # PUT /users/1
  # PUT /users/1.json
  # We don't use this function
  def update
    @user = User.find(params[:id])

    respond_to do |format|
      if @user.update_attributes(params[:user])
        format.html { redirect_to @user, notice: 'User was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @user.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /users/1
  # DELETE /users/1.json
  # Self explanatory. We currently don't use this function, but could if
  # we had reason.
  def destroy
    @user = User.find(params[:id])
    @user.destroy

    respond_to do |format|
      format.html { redirect_to users_url }
      format.json { head :no_content }
    end
  end

end
