package edu.umg.test;

import edu.umg.datos.Conexion;
import edu.umg.datos.UsuarioJDBC;
import edu.umg.domain.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ManejoUsuarios {

    public static void desplegarUsuarios() {
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            UsuarioJDBC usuarioJdbc = new UsuarioJDBC(conexion);

            // Listar los usuarios
            for (Usuario usuario : usuarioJdbc.select()) {
                System.out.println("Usuario = " + usuario);
            }

            conexion.commit();
            System.out.println("Se ha hecho commit de la transacción");

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }

    public static void main(String[] args) {
        desplegarUsuarios();

        Scanner sc = new Scanner(System.in);
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            UsuarioJDBC usuarioJdbc = new UsuarioJDBC(conexion);

            // Insertar un usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setUsername("nombre_usuario3");
            nuevoUsuario.setPassword("contrasena3");
            usuarioJdbc.insert(nuevoUsuario);

            conexion.commit();
            System.out.println("Se ha hecho commit de la transacción");

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }
}

