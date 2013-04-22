class PinsController < ApplicationController
  #Ensures that restric_access function is called before this class can be accessed.
  #restrict_access is defined in ApplicationController
  before_filter :restrict_access
  #This contoller sets the users lat, long, and the message of when they push a pin.
  #We store it in the database for other users to see
  def edit
    @user=current_user
    #Make sure the user exists
    if(@user)
      #Set the values
      @user.lat = params[:lat]
      @user.long = params[:long]
      @user.message = params[:message]
      @user.save
      #Let them know everything went FINE
      render json: {lat: @user.lat, long: @user.long, message: @user.message}
    else
      render json: {error: 'No User Found'}
    end
  end
  #Deprecated
  def destroy
    session[:user_id]=nil
    render json: {logged_out: true}
  end
end
