package br.gov.mt.sefaz.itc.util.sql;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.Date;


public class PreparedStatementUtils
{

  
       private PreparedStatementUtils() {
           throw new AssertionError("Esta é uma classe utilitária e não deve ser instanciada.");
       }

 
   // Métodos utilitários para PreparedStatement com retorno NULL
       public static int setLongOrNull(PreparedStatement ps, int index, Long value) throws SQLException {
           if (Validador.isNumericoValido(value)) {
               ps.setLong(index, value);
           } else {
               ps.setNull(index, Types.NUMERIC);
           }
           return index;
       }
       
   public static int setDominionOrNull(PreparedStatement ps, int index, Integer value, DomnSimNao valueValidador) throws SQLException {
       if (Validador.isDominioNumericoValido(valueValidador)) {
           ps.setLong(index, value);
       } else {
           ps.setNull(index, Types.NUMERIC);
       }
       return index;
   }

       public static int setIntOrNull(PreparedStatement ps, int index, Integer value) throws SQLException {
           if (Validador.isNumericoValido(value)) {
               ps.setInt(index, value);
           } else {
               ps.setNull(index, Types.INTEGER);
           }
           return index;
       }

       public static int setDoubleOrNull(PreparedStatement ps, int index, Double value) throws SQLException {
           if (Validador.isNumericoValido(value) || value == 0) {
               ps.setDouble(index, value);
           } else {
               ps.setNull(index, Types.NUMERIC);
           }
           return index;
       }

       public static int setDateOrNull(PreparedStatement ps, int index, Date date) throws SQLException {
           if (Validador.isDataValida(date)) {
               ps.setDate(index, new java.sql.Date(date.getTime()));
           } else {
               ps.setNull(index, Types.DATE);
           }
           return index;
       }
       
   // Métodos utilitários para PreparedStatement sem Retorno Null
       public static int setLong(PreparedStatement ps, int index, Long value) throws SQLException {
           if (Validador.isNumericoValido(value)) {
              ++index;
               ps.setLong(index, value);
           }
           return index;
       }
       

       public static int setInt(PreparedStatement ps, int index, Integer value) throws SQLException {
           if (Validador.isNumericoValido(value)) {
              ++index;
               ps.setInt(index, value);
           } 
           return index;
       }

       public static int setDouble(PreparedStatement ps, int index, Double value) throws SQLException {
           if (Validador.isNumericoValido(value)) {
              ++index;
               ps.setDouble(index, value);
           } 
           return index;
       }

       public static int setDate(PreparedStatement ps, int index, Date date) throws SQLException {
           if (Validador.isDataValida(date)) {
              ++index;
               ps.setDate(index, new java.sql.Date(date.getTime()));
           } 
           return index;
       }
       
   public static DomnSimNao getDomnSimNaoOrNull(ResultSet rs, String campo) throws SQLException {
          if (rs.getObject(campo) == null) {
              return null;
          } else {
              return new DomnSimNao(rs.getInt(campo));
          }
      }
}
