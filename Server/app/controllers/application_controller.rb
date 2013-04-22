class ApplicationController < ActionController::Base
  protect_from_forgery
  private

  #finds the current user, but ONLY if @user is null
  def current_user
    @user||=User.find(session[:user_id])
  end
  
  #Ensures that the user has a valid access key when making requests
  def restrict_access
    access_key =  ApiKey.find_by_access_token(params[:access_token]) 
    if(access_key)
      session[:user_id] =  access_key.user.id
    end
  end
end
