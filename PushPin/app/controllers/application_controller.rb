class ApplicationController < ActionController::Base
  protect_from_forgery
private

  def restrict_access
   access_key =  ApiKey.find_by_token(params[:access_token]) 
   if(access_key)
    session[:user_id] =  access_key.user.id
     
    end
  end
end
