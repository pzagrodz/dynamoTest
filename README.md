<h1>DynamoDB connection test</h1>

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