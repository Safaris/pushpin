class UserMailer < ActionMailer::Base
  default :from => "PushPin.App@gmail.com"
  #We use a gmail account we created. We use SES to actually send the emails.
  #Check the config file for how to configure SES with rails.
 
  def welcome_email(user)
    #Sends a simple welcome email to the user. The email is formatted in the
    #welcome_email view.
    @user = user
    mail(:to => user.email, :subject => "Thank You for using PushPin!")
  end
  def confirmation_email(user,friend)
    #Sends a confirmation email to the user another user is attempting to 
    #friend. Email (and confirmation link) is formatted in the
    #confirmation_email view.
    @user=user
    @friend=friend
    mail(:to => friend.email, :subject => "You have a friend request on PushPin!")
  end
end
