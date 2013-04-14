class User < ActiveRecord::Base
  has_many :friendshps
  has_many :friends, :through => :friendships
   attr_accessible :password, :username, :lat, :long, :email, :message
   has_one :api_key
   has_secure_password
end
