# Request Filtering
  - Request로 받을 때 각 페이지마다 중복해서 사용되는 것을 편리하게 쓸 때 사용

#### 각 페이지마다 utf-8로 인코딩해주는 것이 중복된다
```
  public void service(HttpServletRequest req, HttpServletResponse res)
  throws ServletException, IOException{
    req.setCharacterEncoding("utf-8");
  }
```

#### (1) 필터링을 해주는 java파일을 만든다
```
package jeong.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class EncodingFilter implements Filter {
	
	private String encoding=null;
	protected FilterConfig filterConfig =null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig=filterConfig;
		this.encoding=filterConfig.getInitParameter("encoding");		
	}	
	
	/*
	 * HttpServletRequest 객체에 web.xml에서 전달된 문자  인코딩을 설정하는 메소드.
	 */	 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,ServletException {
		if(request.getCharacterEncoding()==null) {
			if(encoding!=null) {
				request.setCharacterEncoding(encoding);
			}
		}
		chain.doFilter(request,response);		
	}
		
	public void destroy() {
		this.encoding=null;
		this.filterConfig=null;		
	}	
	
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}
	
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig=filterConfig;
	}
}
```

#### (2) web.wml에서 <filter><filter-mapping>태그 추가
```
  <filter>
    <filter-name>Encoding Filter</filter-name>
    <filter-class>jeong.util.filter.EncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>Encoding Filter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```