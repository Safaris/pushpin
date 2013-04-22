class Friendship < ActiveRecord::Base
  #make sure I can read and edit friend and user ids. This is how I identify
  #friendships
  attr_accessible :friend_id, :user_id
  belongs_to :user
  #The line below is weird if you aren't familiar with rails syntax.
  #Essentially, a friendship belongs to both the user AND the friend
  #who has to be definied as a user.
  belongs_to :friend, :class_name => "User"
end
#These are the two classes of Friendships I user for single table inheritance.
#The attribute type of friendship will determine which of these two
#classes the friendship is (rails magic makes this happen automatically).
#The reason for these classes is so a user can have a friendship pending without
#it showing up when listing friends (see the user model for how).
class PendingFriendship < Friendship
end
class ConfirmedFriendship < Friendship
end
