class AgregarRolesAUsuario < ActiveRecord::Migration[5.2]
  def change
    add_column :usuarios, :es_admin, :boolean
    add_column :usuarios, :es_gestion, :boolean
    add_column :usuarios, :es_tags, :boolean
    add_column :usuarios, :es_consulta, :boolean
  end
end
