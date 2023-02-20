package br.com.email_jax_ws.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.email_jax_ws.conexao.Conexao;
import br.com.email_jax_ws.model.*;

public class DAOMensagem {
    public boolean mandarMensagem(Mensagem msg) throws SQLException {

        String sql = "insert into mensagem (id, remetente_id, destinatario_id, assunto, corpo, data) values "
                + "(nextval('serial'), ?, ?, ?, ?,?);";

        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        long millis = System.currentTimeMillis();
        Date dte = new Date(millis);
        try {

            ps.setInt(1, msg.getRemetente_id());
            ps.setInt(2, msg.getDestinatario_id());
            ps.setString(3, msg.getAssunto());
            ps.setString(4, msg.getCorpo());
            ps.setDate(5, dte);
            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return false;
    }

    public Mensagem mensagem(Integer remetente_id) throws SQLException {
        String sql = "select * from mensagem where "
                + "(destinatario_id = " + remetente_id + ");";

        Connection con = Conexao.getConnection();
        Statement ps = con.createStatement();

        try {

            ResultSet rs = ps.executeQuery(sql);
            Mensagem msg = new Mensagem();
            while (rs.next()) {
                msg.setId(rs.getInt("id"));
                msg.setDestinatario_id(rs.getInt("destinatario_id"));
                msg.setRemetente_id(rs.getInt("remetente_id"));
                msg.setAssunto(rs.getString("assunto"));
                msg.setCorpo(rs.getString("corpo"));
                msg.setData(rs.getDate("data"));

            }
            return msg;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return null;
    }

    public ArrayList<Mensagem> mensagensEnviadas(Integer remetente_id) throws SQLException {
        String sql = "select * from mensagem where "
                + "(remetente_id = " + remetente_id + ");";

        Connection con = Conexao.getConnection();
        Statement ps = con.createStatement();
        ArrayList<Mensagem> msgArray = new ArrayList<Mensagem>();
        try {

            ResultSet rs = ps.executeQuery(sql);
            Mensagem msg = new Mensagem();
            while (rs.next()) {
                msg.setId(rs.getInt("id"));
                msg.setDestinatario_id(rs.getInt("destinatario_id"));
                msg.setRemetente_id(rs.getInt("remetente_id"));
                msg.setAssunto(rs.getString("assunto"));
                msg.setCorpo(rs.getString("corpo"));
                msgArray.add(msg);
            }
            return msgArray;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return null;
    }

    public ArrayList<Mensagem> mensagensRecebidas(Integer destinatario_id) throws SQLException {
        String sql = "select * from mensagem where "
                + "(destinatario_id = " + destinatario_id + ");";

        Connection con = Conexao.getConnection();
        Statement ps = con.createStatement();
        ArrayList<Mensagem> msgArray = new ArrayList<Mensagem>();
        try {

            ResultSet rs = ps.executeQuery(sql);
            Mensagem msg = new Mensagem();
            while (rs.next()) {
                msg.setId(rs.getInt("id"));
                msg.setDestinatario_id(rs.getInt("destinatario_id"));
                msg.setRemetente_id(rs.getInt("remetente_id"));
                msg.setAssunto(rs.getString("assunto"));
                msg.setCorpo(rs.getString("corpo"));
                msgArray.add(msg);
            }
            return msgArray;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return null;
    }

    public boolean deletarMensagem(Integer id) throws SQLException {

        String sql = "delete from mensagem where "
                + "(id = ?);";

        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        try {

            ps.setInt(1, id);
            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return false;
    }
}