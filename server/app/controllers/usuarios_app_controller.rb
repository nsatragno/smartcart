class UsuariosAppController < ApplicationController
  # Desactivar protección contra CSRF porque esta es una API.
  skip_before_action :verify_authenticity_token

  def login
    usuario = UsuarioApp.find_by_email(params[:email])
    render json: {
      ok: !!usuario && usuario.valid_password?(params[:password])
    }
  end

  def signup
    usuario = UsuarioApp.new(
      params.require(:usuario).permit(:nombre, :apellido, :password, :email))

    if usuario.save
      render json: {
        ok: true,
        mensaje: "Usuario creado con éxito"
      }
    else
      render json: {
        ok: false,
        mensaje: usuario.errors.full_messages.join(",")
      }
    end
  end
end
