class User < ActiveRecord::Base
   attr_accessible :password, :username, :lat, :long
   has_one :api_key
   #has_many :client_applications
   #has_many :tokens, :class_name=>"OauthToken",:order=>"authorized_at desc",:include=>[:client_application]
   
   has_secure_password
end
