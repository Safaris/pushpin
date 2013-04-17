ActionMailer::Base.add_delivery_method :ses, AWS::SES::Base,
  access_key_id: ENV['AMAZON_ACCESS_KEY_ID'],
  secret_access_key: ENV['AMAZON_SECRET_ACCESS_KEY']
