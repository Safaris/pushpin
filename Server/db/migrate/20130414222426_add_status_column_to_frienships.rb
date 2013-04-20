class AddStatusColumnToFrienships < ActiveRecord::Migration
  def change
    add_column :friendships, :status, :string, :default => 'PendingFriendship'
  end
end
