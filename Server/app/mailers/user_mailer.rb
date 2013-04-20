class UserMailer < ActionMailer::Base
  default :from => "PushPin.App@gmail.com"
 
  def welcome_email(user)
    @user = user
    mail(:to => user.email, :subject => "Thank You for using PushPin!")
  end
  def confirmation_email(user,friend)
    @user=user
    @friend=friend
    mail(:to => friend.email, :subject => "You have a friend request on PushPin!")
  end
end
