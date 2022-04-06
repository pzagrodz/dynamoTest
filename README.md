<h1>Connection testing</h1>

<h3>Configuration</h3>
Set properties accordingly to your testing needs.
```
test.dynamodb=false
test.webclient=true
```

<h3>Usage</h3>
Copy jar to pod:

```
kubectl cp dynamoTest-1.jar cxcp-expert-engagement-7b899fc98-mfvps:dynamoTest-1.jar -c cxcp-expert-engagement
```

Enter pod:

```
kubectl exec --stdin --tty cxcp-expert-engagement-7b899fc98-mfvps -c cxcp-expert-engagement -- sh 
```

Launch jar:

```
java -jar dynamoTest-1.jar
```