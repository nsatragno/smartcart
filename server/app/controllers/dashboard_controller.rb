class DashboardController < ApplicationController
  before_action :authenticate_usuario!

  def index
    if current_usuario.es_gestion
      redirect_to productos_path
    elsif current_usuario.es_tags
      redirect_to tags_path
    elsif current_usuario.es_admin
      redirect_to usuarios_path
    elsif current_usuario.es_consulta
      sign_out current_usuario
      flash[:error] = "El rol consulta no tiene acciones por el momento"
      redirect_to new_usuario_session_path
    else
      sign_out current_usuario
      flash[:error] = "El usuario no tiene ningún permiso asociado"
      redirect_to new_usuario_session_path
    end
  end
end
