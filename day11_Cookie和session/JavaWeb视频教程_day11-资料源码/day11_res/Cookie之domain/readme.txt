1. ��C:\WINDOWS\system32\drivers\etc\hosts�ļ��������������
127.0.0.1	news.qdmmy6.com
127.0.0.1	tieba.qdmmy6.com

2. ��${CATALINA_HOME}/conf/server.xml���������
<Host name="news.qdmmy6.com"  appBase="news"
      unpackWARs="true" autoDeploy="true">
</Host>
<Host name="tieba.qdmmy6.com"  appBase="tieba"
      unpackWARs="true" autoDeploy="true">
</Host>

3. ��tieba��news����Ŀ¼copy��${CATALINA_HOME}��

4. ����http://news.qdmmy6.com/SaveServlet

5. ����http://tieba.qdmmy6.com/GetServlet