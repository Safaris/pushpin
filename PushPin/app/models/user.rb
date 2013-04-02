class User < ActiveRecord::Base
   attr_accessible :password, :username, :password_confirmation
   has_secure_password
end
