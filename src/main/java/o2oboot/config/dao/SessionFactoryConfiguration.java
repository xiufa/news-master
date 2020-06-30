package o2oboot.config.dao;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration

public class SessionFactoryConfiguration {

    private static String mybatisConfigFile;
    private static String mapperPath;
    @Autowired
    private DataSource dataSource;

    @Value("${type_alias_package}")
    private String typeAliasPackage;


    @Value("${mybatis_config_file}")
    public void setMybatisConfigFile(String temp){
        SessionFactoryConfiguration.mybatisConfigFile=temp;
    }

    @Value("${mapper_path}")
    public void setMapperPath(String temp){
        SessionFactoryConfiguration.mapperPath=temp;
    }




    @Bean(name="sqlSessionFactory")
    public SqlSessionFactoryBean getSqlSessionFactory() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFile));

        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));

        // 设置typeAlias 包扫描路径
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);
        return sqlSessionFactoryBean;
    }

}
