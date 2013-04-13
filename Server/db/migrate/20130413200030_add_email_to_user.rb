class AddEmailToUser < ActiveRecord::Migration
  def up
    change_table :users do |t|
      t.string :email
    end
  end
  def down
    remove_column :users, :email
  end
end
