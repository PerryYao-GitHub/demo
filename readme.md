# SpringBoot 2.7.x + Vue3 前后端分离 脚手架项目
## 技术栈
**前端**
尚未更新
**后端**
- SpringBoot 2.7.x
- MyBatis Plus
- JWT token
- MySQL
- Swagger 2 (springdoc)

## 脚手架功能
- 身份校验: Admin & User
- Admin 管理用户
- User 登录, 注册, 修改个人信息, 修改密码, 销户

## 可在此项目基础上快速部署
- 替换数据库的库名和密码
- 在开发环境中, 为了方便使用swagger, `WebInterceptorConfig` 被打上了`@Profile("dev")`
- 因隐私问题, `properties.yml` 被略去, 可copy `properties-prod.yml` 中的内容再加以补充
