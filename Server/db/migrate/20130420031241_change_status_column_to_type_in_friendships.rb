class ChangeStatusColumnToTypeInFriendships < ActiveRecord::Migration
  def change
    rename_column :friendships, :status, :type
  end
end
