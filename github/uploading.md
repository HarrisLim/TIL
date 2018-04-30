# github 업로드하는 법

  1. git config --global user.name "xx"

  2. git config --global user.email "xxxxxx@gmail.com"

  3. git init

  4. git status

  5. git add.

  6. git commit -m "변경내용"

  7. git remote add origin https://github.com/username/xxx.git

  8. git remote -v

  9. git push -u origin master
  

### 다른 컴퓨터에서 새로운 Repo의 파일 가져오기
  1. git init
  2. git remote add origin https://github.com/username/xxx.git
  3. git pull --rebase origin master // 그냥 --rebase로는 안된다. * current branch를 써줘야해