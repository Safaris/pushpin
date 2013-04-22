class ApiKey < ActiveRecord::Base
  belongs_to :user
  before_create :generate_access_token

private

#generates an access token for the user authentication. Creates a random hex
#value for the access token. This is passed in requests which require 
#the user to be logged in. It's checked in the method restrict_access in
#the application controller
  def generate_access_token
    begin
      self.access_token = SecureRandom.hex
    end while self.class.exists?(access_token: access_token)
  end
end
