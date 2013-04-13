class PinsController < ApplicationController
  before_filter :restrict_access
  def edit
    @user=current_user
    if(@user)
      @user.lat = params[:lat]
      @user.long = params[:long]
      @user.message = params[:message]
      @user.save
      render json: {lat: @user.lat, long: @user.long, message: @user.message}
    else
      render json: {error: 'No User Found'}
    end
  end
  def destroy
    session[:user_id]=nil
    render json: {logged_out: true}
    #redirect_to root_path, :notice => 'Logged out.'
  end
end
