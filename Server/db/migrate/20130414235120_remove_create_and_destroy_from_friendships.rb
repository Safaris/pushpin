class RemoveCreateAndDestroyFromFriendships < ActiveRecord::Migration
  def up
    remove_column :friendships, :create
    remove_column :friendships, :destroy
  end

  def down
    add_column :friendships, :destroy, :string
    add_column :friendships, :create, :string
  end
end
