package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Contacto;

public class AgendaContactos {
	
	private String url="jdbc:mysql://localhost:3307/agenda";
	String user="root";
	String pwd="root";
	static{
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}        
    }
	
	/*static DataSource ds;
	static{
    	try {
			Context ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/refcontactos");
		} catch (NamingException e) {			
			e.printStackTrace();
		}        
    }*/
	public void agregar(Contacto con){
		
		//try(Connection cn=ds.getConnection();) {
        try(Connection cn=DriverManager.getConnection(url,user,pwd);) {                                   
            String sql="Insert into contactos (nombre,email,edad) ";
            sql+="values(?,?,?)";
            //creamos consulta preparada:
            PreparedStatement ps=cn.prepareStatement(sql);
               //Sustituimos parametros por valores
               ps.setString(1, con.getNombre());
               ps.setString(2, con.getEmail());
               ps.setInt(3, con.getEdad());
               //ejecutamos
               ps.execute();            
        }  catch (SQLException ex) {
            ex.printStackTrace();
        }      
    }
	public void eliminarContacto(int id) {
		//try(Connection cn=ds.getConnection();) {
    	try(Connection cn=DriverManager.getConnection(url,user,pwd);) {                       
            String sql="Delete from contactos where idContacto=?";           
            //creamos consulta preparada:
            PreparedStatement ps=cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            
        }  catch (SQLException ex) {
            ex.printStackTrace();
        }     
    }
	public List<Contacto> recuperarContactos(){        
        List<Contacto> lista=new ArrayList<>(); 
      //try(Connection cn=ds.getConnection();) {
        try(Connection cn=DriverManager.getConnection(url,user,pwd);) {                                   
            String sql="select * from contactos";
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);            
            while(rs.next()){
                lista.add(new Contacto(rs.getInt("idContacto"),rs.getString("nombre"),rs.getString("email"),rs.getInt("edad")));
            }
        }  catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return lista;
    }
	public List<Contacto> buscarContactos(String cad){        
        List<Contacto> lista=new ArrayList<>(); 
      //try(Connection cn=ds.getConnection();) {
        try(Connection cn=DriverManager.getConnection(url,user,pwd);) {                                   
            String sql="select * from contactos where nombre like ?";
            PreparedStatement ps=cn.prepareStatement(sql);
            ps.setString(1, "%"+cad+"%");
            ResultSet rs=ps.executeQuery();            
            while(rs.next()){
                lista.add(new Contacto(rs.getInt("idContacto"),rs.getString("nombre"),rs.getString("email"),rs.getInt("edad")));
            }
        }  catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return lista;
    }
}
