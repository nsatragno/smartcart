class ApplicationController < ActionController::Base
  def validar_rol(rol)
    unless current_usuario.send "es_#{rol}"
      render plain: "No tiene los permisos para ver esta página", status: :forbidden
    end
  end

  def validar_admin
    validar_rol(:admin)
  end

  def validar_cajera
    validar_rol(:cajera)
  end

  def validar_tags
    validar_rol(:tags)
  end

  def validar_gestion
    validar_rol(:gestion)
  end
end
