#!/bin/bash

yum update
yum install docker -y
usermod -aG docker ec2-user
systemctl enable docker
systemctl start docker

yum install git -y

cat << HEREDOC > /root/.ssh/id_rsa
-----BEGIN OPENSSH PRIVATE KEY-----
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
-----END OPENSSH PRIVATE KEY-----
HEREDOC

chmod 0400 /root/.ssh/id_rsa

mkdir -p /src/my-frontend-app

cd /src/my-frontend-app

cat << HEREDOC > ./UlviAbbTechKey.pem
-----BEGIN RSA PRIVATE KEY-----
MIIEogIBAAKCAQEAjtseDrkCgB4UER0mKqpB8QeHqjZvATm9qXgMpyDC6BgUvb3C
NUpJ8cQsrCYOS+IxLww/mvHZtRl0o9o/CYIqq/lchH2pGXy9tSxi/kRuN4NNqIUo
4w7s94ZgWGal+97tZfZ+29G0SLzhuZzx7xiGPbxRNrKnN85hV3JfTh3q3OOm8W/O
nYQWIW6JlT1CISwtMg6wllqXjwcWHhDXTTk1zqj2u4QY4xyMhOeGGwCeFXjSwNOU
RSp3NwaM0p+OSoRYFJAyNOVvIxEv/wXsadbyQ7KQlDRxSyGCj6n1MddlMrjRUWNg
4s1sEP93wdRsh2HkVnk/fU6CoqZ4Y113s+57tQIDAQABAoIBAElU/cb8/eWaix6F
4hPAtnzuKpwbuTvd0hhugcBBNwxBYFROVqVYb0DmPTBZkLkvcaP/G5+ESweF+7Qo
0nclFJaXOQDiKK3mtNON53sy0Yk5o+HACU8VWFtv5YE7oDT9KMxQjqp0qaE1JIJN
qHveEPsb8myrpN8m2B01nurQvMGKbdzp5ZkT/gbTwrB5vvv/I8CEhyc0SKdn+EJA
7uPefh209YPunuwwW7Y+GzRhvxT15XcY9kGP59GF2WIqd+R+IWr1v16jHb/mvXby
DxBaklwjuBm7M2LrN0N9OnhM5dQIZe8gnlfkHmsqFnmn3IbYr+wqhwrkGAMgA9I+
DWF3U4ECgYEA0v73Og/FyRhrqO5ZJ5ke4XrvJx7hjR3/ede9VHG3pLDhTp1vfYfw
YrKHCbF2ZSvw9ccbqxX3i9lkP9Prgu8hZjb52R8oxQK1MAoBLaSf1RxHQbXkjiPU
cKKUc6xDqS7iH7rCgXGG635QFSKhj8DZlrMJLkpveT5PrJ4XoTu7v7ECgYEArVN+
kp0KqWyHxi8k4U9IqtJoqsiExpjmIYviLmaVK0yZzryZ70SXxzZ9RPsOtVprGIzx
x1kv5UCFvi3Wf/kxYH/7K/zlT88GYdVWB7o62jMq7fkG33g3XcHjd0hugfJ40p4N
FZ5V5s4Q6WtMkOcu7lhngT72NV2kd4f0xMX+IUUCgYBP8ku6tD888wPX/nfJiTsq
NGppaEoOLk+e2x/h3zS2Mk8senrEFb3/p6WDBrrVmtCyT78sFCa31qcliCa0decx
06LXofbQrzX3/dJ0eOwW06AsRb9UHD9ibjfRnHQGpr0HSLxqT5nPoQy29WhCD9Bi
SXQxFgMm7XVC8qQBc6CwAQKBgFvd1UiAVBZYn947PqDtFOb1fuSGQ1m/0LoXGlrd
pFK+1wbsj+pxwMCl8N+O8czNj9YDXmL6spggCsMyKG7L9Y/gRZCbCi+y2v8fw+l0
m5zrYeWgCDSJ006uZZHjoi7eBYwcmXLL6Vvb/Lq7VGm5HvbVhIbMiFLgvztbpDBn
gLrBAoGAUnK5zbgvU+NlKMfDOt5d3taGZFMCLBt3n5lTq4a+7To78YBzzS47dRfj
YfZBvZ6toB1z1GgZ4hiZJTo1Po3Ydyxw9l7PFGaI8WH7P1webGFgukOTQXftt86b
8S7ieIY/Baj9uliCAH8SizwqtI4jS7UFhzzH77rxVFQ8ByTQMbU=
-----END RSA PRIVATE KEY-----
HEREDOC

ssh-keyscan github.com >> /root/.ssh/known_hosts
git clone https://github.com/uijdeveloper/abbtech-finalproject.git

cd abbtech-project/frontend

docker build -t my-frontend-app:v1.0.0 .
docker run -di -e BACKEND_API=${backend_url}  --name  my-frontend-app -p 80:80 my-frontend-app:v1.0.0

