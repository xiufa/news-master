package o2oboot.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private String[] encryptPropNames={"jdbc.username","jdbc.password"};

    @Override
    protected String convertProperty(String propertyName,String propertyValue){

        if(isEncryptProp(propertyName)){
            String res=DESUtil.getDecryptString(propertyValue);
            return res;
        }else{
            return propertyValue;
        }

    }

    private boolean isEncryptProp(String name){

        for(int i=0;i<encryptPropNames.length;i++){

            if(encryptPropNames[i].equals(name)){
                return true;
            }
        }
        return false;
    }


}
