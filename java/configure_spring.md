# Spring 초기 설정
  - version
    - STS ver 3.9.4
    - java 9

### 프로젝트 생성
  - Spring Legacy Proejct
  - Project name을 설정하고, Spring MVC Project로 생성
  - 패키지 설정에서 aaa.bbb.ccc에서 ccc는 localhost:8080/ccc로 기본설정 된다.

### 구조 설명
  - src/main/java : java file을 넣는다. model, controller, module
    - HomeController.java를 보면 @RequestMapping에서 Mapping을 할 수 있다. 
  - src/main/resources : 위의 java파일에서 DB를 핸들링하는 SQL파일 관련된 것들이 들어간다.
  - src/test/java : 과정을 테스트하는 것. 어느 단계까지의 테스트 (JUnit : 단위테스트 framework)
  - src/test/resources : main/resources와 같다
  - JRE System Library : build path한 것들
  - Maven Dependencies :  경로만 잡아놓고, Maven이 jar파일을 웹에서 다운받는다.
    - Maven : biuld path를 관리해주는 녀석<br>
      external JARs를 하는 것인데, 그 경로를 고정적으로 잡아주는 것.<br>
      그래서 프로젝트 폴더는 가벼워진다.<br>
      자기가 알아서 classpath를 잡아줘.
  - src : view 관련 폴더
    - src/main/webapp
      - view파일들이 들어가
    - src/main/webapp/resources
      - 이미지, 동영상 등을 저장
    - src/main/webapp/WEB-INF/spring
      - spring 관련 설정 파일이 있다
    - src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml
      - eclipse에서 mapping해주던 것을 controller관련 설정을 여기서 한다.
      - prefix와 suffix가 있는데 이걸 쓰는 이유는 /WEB-INF/views/xxx.jsp를 쓰기 귀찮아서 그걸 따로 뺀거야
      - context:conponent-scan이란, aaa.bbb.ccc에 등록된 파일만 Spring으로 간주해. (Spring 객체를 생성)
    - src/main/webapp/WEB-INF/spring/root-context.xml
      - 서버가 시작되면 web.xml실행 후 얘가 실행돼. DB관련 설정을 한다.
  - pom.xml
    - 스프링 컨테이너에 대한 설정
    - dependencies에 추가하면 Maven Dependencies에 자동으로 추가된다.

### 인코딩 설정
  - web.xml의 <web-app>태그 내에서 최상단에 추가 
  - forceEncoding: 입력한 인코딩으로 강제셋팅의 유무 설정 가능. default: false<br>
    HttpServletRequest객체와 response객체도 미리 셋팅해버린다. 그러므로 무조건 좋은 것은 아니다.
```
  <!-- Encoding Filer Start -->
  <filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value> 
    </init-param>
    <init-param>
      <param-name>forceEncoding</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>

  <filter-mapping>
    <filter-name>encodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```

### DB연동
#### root-context.xml
```
  (1)
    <bean id="dataSource" class="org.apache.tomcat.dbcp.dbcp.BasicDataSource" destroy-method="close">
      <property name="driverClassName" value="oracle.jdbc.OracleDriver"/>
      <property name="url" value="jdbc:oracle:thin:@localhost:1521:JAVA"/>
      <property name="username" value="spring"/>
      <property name="password" value="java"/>
    </bean>

  (2)
    // ref="dataSource"로 위의 id="dataSource"를 참조해서 sqlSessionFactory를 만드는 것
    // sqlSession을 사용하기 위해 추가
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
      <property name="dataSource" ref="dataSource"/>
      <property name="configLocation" value="classpath:jeong/spring/mybatis/mybatis-config.xml"/>
    </bean>
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
      <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>

  (3)
    // 이 코드를 추가하면 에러가 날거야. 그러면 Namespaces탭에서 context추가.
    // 어노테이션 기반으로 객체 자동 생성하는 설정
    <context:annotation-config/>
    <context:component-scan base-package="jeong.spring3">
      <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>
```

#### pom.xml
  - 사용하고 싶은 기능을 mavenrepository.com에서 다운받을 수 있다
```
 (1) tomcat-dbcp
    <dependency>
      <groupId>org.apache.tomcat</groupId>
      <artifactId>tomcat-dbcp</artifactId>
      <version>7.0.41</version>
    </dependency>

  (2) mybatis
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.1.1</version>
    </dependency> 

  (3) mybatis-spring
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.1.1</version>
    </dependency>

  (4) for Junit Test
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>${org.springframework-version}</version>
    </dependency>
```

#### 스프링이 내부적으로 하는 일. (DB관련)
```
1. BasicDataSource dataSource = new BasicDataSource(className, url, user, pwd);
2. SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean(dataSource, configLocation);
3. sqlSession = new SqlSessionTemplate(sqlSessionFactory);

이렇게 sqlSession이 만들어져서 sqlSession만 사용하면 된다. 아주 간편하지 !
```

#### mybatis 설정
  1. src/main/resources에 jeong.spring.mybatis 패키지 생성
  2. jeong.spring.mybatis 내에 mybatis-config.xml파일 생성
  3. mybatis-config.xml에 추가
```
      <?xml version="1.0" encoding="UTF-8"?>
      <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-config.dtd">
      <configuration>
        <typeAliases>
          <typeAlias alias="Address" type="jeong.spring3.model.Address"/> // 여기 있는 Address가 DTO야
        </typeAliases>
        <mappers>
          <mapper resource="jeong/spring/mybatis/Address.xml" />
        </mappers>
      </configuration>
```
  4. jeong.spring.mybatis 내에 Address.xml파일 생성
  5. src/main/resources에 jeong.spring3.model 패키지 생성
  6. Address.xml에 추가
```
      <?xml version="1.0" encoding="UTF-8" ?>
      <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
        <mapper namespace="jeong.spring.mybatis.address">
          <resultMap id="addrResult" type="Address">
            <result property="seq" column="SEQ"/> // column : DB에 있는 컬럼
            <result property="name" column="NAME"/> // property : DTO에 있는 멤버변수
            <result property="addr" column="ADDR"/>
            <result property="rdate" column="RDATE"/>
          </resultMap>
          <select id="mySelectAll" resultType="Address">
            select * from ADDRESS order by seq desc
          </select>
          <insert id="myInsert" parameterType="Address">
            insert into ADDRESS values(ADDR_SEQ.nextval, #{name}, #{addr},SYSDATE)
          </insert>
          <delete id="myDelete" parameterType="long">
            delete from ADDRESS where SEQ=#{seq}
          </delete>
      </mapper>
```
  7. jeong.spring3.model에 DTO를 만들자. 이름은 Address (이름: AddressDTO X)
  8. DB도 맞게 만들어. 물론 저 위에 있는 조건은 DB가 만들어진 후에 맞춰서 하겠지만 이건 test니까.
  9. 이제 테스트를 위해 JUnit을 사용한다
  10. src/test/java의 jeong.spring3.test에 DataSourceTest클래스를 만들고 밑의 코드 복붙.
```
      package jeong.spring3.test;

      import java.sql.Connection;
      import javax.inject.Inject;
      import javax.sql.DataSource;
      import org.junit.Test;
      import org.junit.runner.RunWith;
      import org.springframework.test.context.ContextConfiguration;
      import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

      @RunWith(SpringJUnit4ClassRunner.class)
      @ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
      public class DataSourceTest {
        @Inject
        private DataSource ds;
        @Test
        public void testConection() throws Exception{
          try{
            Connection con = ds.getConnection();
            System.out.println("#### ds: " + ds);
            System.out.println("#### con: "+con);
          }catch(Exception e){
            e.printStackTrace();
          }
        }
      }
```

### Model and Controller 생성
#### DAO 생성
  - jeong.spring3.addr.model.dao.AddrDao 
  - 클래스를 구현하기 전에 Interface를 먼저 구현해야해.
  - interface로 만드는 이유는 스프링에게 권한을 주기 위하여. 그래야 DAO, Service 이런 것들이 완벽히 끊어져. 
  - DAO는 Spring에서 @Repository로 쓴다. (@Controller처럼)
  - @Repository("xx") xx라고 이름 지을 수 있다.

#### Service 생성
  - jeong.spring3.addr.model.service.AddrService
  - 클래스를 구현하기 전에 Interface를 먼저 구현해야해.
  - Service는 스프링 객체로 만들어주려면 @Service를 추가 해야한다.



#### Controller 생성
  - jeong.spring3.addr.controller
  - controller는 interface로 안만들어.
  - Controller는 Spring 객체로 만들려면 servlet-context.xml에 직접 추가해야된다.

### STS에서 java버전 변경
  1. 해당 프로젝트의 properties -> Project Facets -> Runtimes탭을 선택
  2. Apache Tomcat 선택 -> 왼쪽 뷰에서 Java Version 변경 가능

### 잡다한 문법
#### Request.getParameter 대신 @RequestParam을 쓴다.
  - ex
    ```
      Request.getParameter("xx");
      @RequestParam(xx) String(any type) xx;
    ```

#### @Autowired는 스프링이 만든 interface객체를 내가 선언한 객체에 집어넣는거야.
  - ex
    ```
      private AddrDao addrDao; 
    ```
