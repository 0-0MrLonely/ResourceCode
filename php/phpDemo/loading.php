<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>��¼����</title>
		<style type="text/css">
			.main{
				margin: 0 auto;
				padding: 10px;
				width: 250px;
				height: 200px;
				background: cornflowerblue;
			}
			.leftbar{
				width: 30%;
				padding-bottom: 15px;
				display: inline-block;
				text-align: right;
			}
			.bottom{
				padding-bottom: 15px;
			}
		</style>
	</head>
	<body>
		
		<form action="��¼��֤.php" method="post">
			
			<div id="main" class="main">
				<h3>
					�������û���
				</h3>
				<div>
					<label><div class="leftbar">�û�����</div><input type="text" name="userName" /></label>
					<label><div class="leftbar">���룺</div><input type="text" name="userPassword" /></label>
				</div>
				<div class="bottom">
					<div class="leftbar"></div><input type="radio" name="remmber"  />��ס��һ��
				</div>
				<div class="bottom">
					<div class="leftbar"></div><input type="submit" name="submit" value="��¼" />
				</div>
				
			</div>
			
		</form>	
	</body>
</html>
 
 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>��¼��֤</title>
		<style type="text/css">
			.main{
				margin: 0 auto;
				width: 350px;
				height: 100px;
				background: cornflowerblue;
				padding: 20px;
			}
		</style>
	</head>
	<body>
		<div class="main">
			<?php
 
				$passname=$_POST['userName'];//�˺�
				$password=$_POST['userPassword'];//����
 
                if($passname==null||$password==null){
					header("location:loading.html");//ֱ�Ӵ򿪸�php�ļ�����ת����¼����
				}
				
				
//				require_once('��¼��֤���ݿ�����.php');
//				$db=new connectDB();
//				$db->getConn();
			
				//	$db=@new mysqli('localhost','root','root');//��������
				//	if ($db->connect_error)
				//	 die('����: '. $db->connect_error);
				//	$db->select_db('�����������ݿ�');//***
			    require_once "connet.php";
		      
		        $db=@new mysqli('localhost','root','root');
		       
				if ($db->connect_error)
				 die('����: '. $db->connect_error);
					$sql='SELECT * FROM pass WHERE name='."'{$name}'AND psw="."'$password';";//����������
					$result=$db->query($sql);
					$num_users=$result->num_rows;//�����ݿ������������ϵ��û�
					if($num_users!=0)
						echo "<h3>��ӭ��</h3>";
					else{
						echo "�û������������";
					}
			?>
		</div>
	</body>
</html>
 
