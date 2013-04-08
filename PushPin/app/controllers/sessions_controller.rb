class SessionsController < ApplicationController
  def create
    user=User.find_by_username(params[:username])
    if(user && user.authenticate(params[:password]))
      #Create session, store logged in ID
      session[:user_id]=user.id
      render json: {logged_in: true, session_key: session[:user_id]}
    else
      render json: {logged_in: false, session_key: nil}
    end
  end
  def destroy
    session[:user_id]=nil
    render json: {logged_out: true}
    redirect_to root_path, :notice => 'Logged out.'
  end
end
