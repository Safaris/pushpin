class SessionsController < ApplicationController
  def create
    user=User.find_by_username(params[:username])
    if(user && user.authenticate(params[:password]))
      #Create session, store logged in ID
      session[:user_id]=user.id
      redirect_to user_path(user), :notice => 'Logged in'
    else
      flash.now.alert = 'PASSWORDS WRONG, BITCH'
      render 'new'
    end
  end
  def destroy
    session[:user_id]=nil
    redirect_to root_path, :notice => 'Logged out.'
  end
end
