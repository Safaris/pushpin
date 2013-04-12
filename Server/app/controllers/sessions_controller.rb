class SessionsController < ApplicationController


  def create
    user=User.find_by_username(params[:username])
    if(user && user.authenticate(params[:password]))
      #Create session, store logged in ID
      render json: {logged_in: true, access_token: @user.api_key.auth_token}
    else
      render json: {logged_in: false, access_token: nil}
    end
  end
  def destroy
    render json: {logged_out: true}
    #redirect_to root_path, :notice => 'Logged out.'
  end



end
