class AddLatLongUser < ActiveRecord::Migration
  def up
    change_table :users do |t|
      t.decimal :lat, :precision => 15, :scale => 10
      t.decimal :long, :precision => 15, :scale => 10
    end
  end

  def down
    remove_column :users , :lat
    remove_column :users , :long
  end
end
