1. 在C:\WINDOWS\system32\drivers\etc\hosts文件中添加如下内容
127.0.0.1	news.qdmmy6.com
127.0.0.1	tieba.qdmmy6.com

2. 在${CATALINA_HOME}/conf/server.xml中添加配置
<Host name="news.qdmmy6.com"  appBase="news"
      unpackWARs="true" autoDeploy="true">
</Host>
<Host name="tieba.qdmmy6.com"  appBase="tieba"
      unpackWARs="true" autoDeploy="true">
</Host>

3. 把tieba和news两个目录copy到${CATALINA_HOME}下

4. 访问http://news.qdmmy6.com/SaveServlet

5. 访问http://tieba.qdmmy6.com/GetServlet