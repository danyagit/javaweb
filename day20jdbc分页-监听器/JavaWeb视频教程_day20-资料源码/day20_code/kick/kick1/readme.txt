��¼�ɹ�����ʾ�����û�

1. login
2. login()
3. users.jsp

UserServlet#login()
1 ��ȡ�û���
2 ����User����ȫ���ݣ�ip��logintime��
3 ���浽session��
4 ת����users.jsp

UserListener
add()
key: "user"
value: User����

1 �鿴servletContext���Ƿ����users
2 ��������ڣ�����users = new Map<String,User>();
3 ����ӵ�User�����ŵ�users��
4 ��users�ŵ�ServletContext��

users.jsp
ѭ����ʾServletContext�е�users��

=================================

UserListener
replace()
key: "user"
value: ��User����
1 �鿴servletContext���Ƿ����users
2 ��������ڣ�����users = new Map<String, User>();
3 ��ȡsession����session�л�ȡ"user"������User����
4 ��users���Ƴ���User�����ٱ�����User����

==================================

