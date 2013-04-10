class User < ActiveRecord::Base
   attr_accessible :password, :username, :lat, :long
   has_secure_password
end
