class AddMessageToUser < ActiveRecord::Migration
  def up
     change_table :users do |t|
      t.string :message
    end
  end
  def down
    remove_column :users, :message
  end
end
