class SessionsController < ApplicationController
#This is the controller a user uses to log into the system

  def create
    #find the user by the username provided
    user=User.find_by_username(params[:username])
    #Make sure the user exists, then make sure the passwords match.
    #The order of this if expression (and the use of && instead of and is VERY important)
    if(user && user.authenticate(params[:password]))
      #Return the users access token to the app. The app will pass it with requests which need to be logged in for.
      render json: {logged_in: true, access_token: user.api_key.access_token}
    else
      render json: {logged_in: false, access_token: nil}
    end
  end
  #Not much to do on server side when a user logs out.
  #The app just needs to forget the access token.
  def destroy
    render json: {logged_out: true}
    #redirect_to root_path, :notice => 'Logged out.'
  end
end
