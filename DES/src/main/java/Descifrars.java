
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Descifrars {
    boolean FUR = true;
    boolean Descifrararch(SecretKey clavef, String path) throws Exception {
      try{
          
       
      Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");
          
      cifrado.init(Cipher.DECRYPT_MODE, clavef);
      
      byte[] buffer = new byte[1000]; // leer cada mil
         byte[] bufferPlano;
         
         

         
         FileInputStream in = new FileInputStream(path);
         FileOutputStream out = new FileOutputStream(path+".descifrado");
         

         
         int bytesleidos = in.read(buffer, 0, 1000);

         
         while(bytesleidos != -1){

             
             bufferPlano = cifrado.update(buffer, 0, bytesleidos);

             out.write(bufferPlano);
             bytesleidos = in.read(buffer, 0, 1000);
         }

         bufferPlano = cifrado.doFinal();

         
         out.write(bufferPlano);
         
         in.close();
         out.close();
         
      }catch(InvalidKeyException e){
          System.out.println("Error");
          JOptionPane.showMessageDialog(null,"Error\n FAVOR DE INGRESAR CLAVE DE 8 BYTES");
           System.out.println(e);
           FUR = false;
           
    }
      return FUR;
    
      
}
    
   SecretKey originalKey;
    public  SecretKey convertStringToSecretKeyto(String claves) {
        try{
    byte[] decodedKey = Base64.getDecoder().decode(claves);
     originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
    
        }catch(Exception e){
         originalKey = null;
        }
        return originalKey;
}
    String ubi;
     public String SubiraArchivo(){
      
      JFileChooser file = new JFileChooser();
      
      file.showOpenDialog(file);
      File abre = file.getSelectedFile();
      
      if(abre!= null){
          ubi = abre.getPath();
      }
      System.out.println(ubi);
     return ubi;
  }
                     
}
