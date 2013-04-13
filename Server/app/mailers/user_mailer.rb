class UserMailer < ActionMailer::Base
  default :from => "PushPin.App@gmail.com"
 
  def welcome_email(user)
    @user = user
    mail(:to => user.email, :subject => "Thank You for using PushPin!")
  end
end
