class PinsController < ApplicationController
  def edit
    @user = User.find(session[:user_id])
    if(@user)
      @user.lat = params[:lat]
      @user.long = params[:long]
      @user.save
      render json: {lat: @user.lat, long: @user.long}
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
