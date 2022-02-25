# program-knowledge
> The way of code
---
## git提交规范
> commit规范

Commit message 都包括三个部分：header body footer
```
# header (必需)
<type>(<scope>): <subject>
空一行
# body (可选)
<body>
空一行 (可选)
# footer
<footer>

示例：
fix(xx模块): 修复xxxbug

xxx.java 改动xxx

close #123 --issue id
```
### header
**type**：说明 commit 的类别
```
feat      新功能
fix       修补bug
docs      文档
style     格式(不影响代码运行的变动)
refactor  重构(即不是新增功能, 也不是修改bug的代码变动)
test      增加测试
```
**scope**：说明 commit 影响的范围  
**subject**：说明 commit 目的(简短描述，不超过50个字符)  
### body
**body** 部分是对本次 commit 的详细描述(可以分成多行)
### footer
**footer** 部分只用于以下两种情况
1. 不兼容变动
> 如果当前代码与上一个版本不兼容，则 footer 部分以BREAKING CHANGE开头，后面是对变动的描述、以及变动理由和迁移方法
2. 关闭 Issue
> 如果当前 commit 针对某个issue，那么可以在 footer 部分关闭这个 issue 
---
## git分支规范
> git 分支分为集成分支、功能分支和修复分支，分别命名为 dev(develop)、feature 和 hotfix，均为单数
### 集成分支
dev：所有分支集成的地方（处主分支），相当于灰度环境用的分支，所以上线代码从 dev merge 到 master
### 功能分支
feature/xxx：新的功能、模块开发分支
### 修复分支  
hotfix/xxx：修复紧急线上 bug 的分支（不紧急的 bug 归为功能分支）

---
*建议*  
功能分支合并集成分支使用 merge  
从集成分支拉代码使用 rebase  
[区别](https://www.jianshu.com/p/4079284dd970)