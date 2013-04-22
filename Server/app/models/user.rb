class User < ActiveRecord::Base
  #Next 3 lines should be obvious. A user has many friendships, including confirmed and pending ones.
  has_many :friendships
  has_many :confirmed_friendships
  has_many :pending_friendships
  #This is the cool line. A user ONLY has friends through their confirmed friendships.
  #That means that a user can have any number of pending/unconfirmed friendships but
  #they won't count towards the actual friends listed until the Friendship type
  #is changed to ConfirmedFriendship.
  has_many :friends, :through => :confirmed_friendships
  #Need these attributes for various purposes. Should be obvious where/why
   attr_accessible :password, :username, :lat, :long, :email, :message
   #api_key is the access token. There's some discrepency in the naming
   #conventions.
   has_one :api_key
   #This makes sure the password is stored and checked as a hashed value.
   #Essentially, it's rails magic
   has_secure_password
end
