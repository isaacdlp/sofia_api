# Sofia API

Specifications and sample code for the API at [sofia.conexo.vc](http://sofia.conexo.vc).

Permanent git repository hosted at [GitHub](https://github.com/isaacdlp/sofia_api/).

Please send questions, comments and ideas to [admin@conexo.vc](mailto:admin@conexo.vc).

## Generic

### Sofia ID

The Sofia ID (**SID**) is unique token that identifies your company and allows you to interact with the Sofia platform.

A Sofia ID typically looks like this:

```
0nl2l0x06HGV9Jog6cAbkg
```

Please note that **this sample is not valid**. Reach out to the [administrator](admin@conexo.vc) in order to create or change your SID.

You must include your SID on each server request, in **at least one** of three possible ways:

* In the url path:

```
http://sofia.conexo.vc/api/0nl2l0x06HGV9Jog6cAbkg
```

* In the url query string:

```
http://sofia.conexo.vc/api?sid=0nl2l0x06HGV9Jog6cAbkg
```

* In the data fields of the POST payload:

```
sid: 0nl2l0x06HGV9Jog6cAbkg
```

The SID is always a web-safe string that does **not** need to be encoded further for HTTP submission.

### Dates

Currently metrics are computed **monthly**, so only the **year** and **month** sections are meaningul. The **day is discarded** and automatically set to the first day of the month (01).

For instance:

```
2020.05
2020.05.01
2020.05.12
```

All the above are valid dates, and they all refer to **May 2020**, discussed as "the Metrics for May" and stored internally as "2020.05.01" because that is how the terms are used in common language, even though the metric would strictly correspond to a measure performed by the end of May or beginning of June.

Dates in Excel are extracted as objects regardless of their visible format and thus don't require any special preparation.

## File Upload API

Use this API to submit your entire Excel sheet to Sofia, as you would if using the standard [Metrics Web UI](http://sofia.conexo.vc/metrics).

This is the best option if you actively use the Sofia Excel Template to track your company's progress but want to automate the delivery step.

### Endpoint

```
http://sofia.conexo.vc/xls
```

### Request

Perform a multi-part HTTP POST submission with the Excel file as the binary body of the request.

Optionally, you can add the **all_dates** parameter in the data fields to extract metrics from all dates found in the spreadsheet. Otherwise, metrics are extracted **only for the most recent date found**.

```
all_dates: true
```

### Response

You will receive a [standard HTTP response status code](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) depending on the results of your action.

* Success (**200**): Successfully extracted at least one metric from the request.
* Error (**400**): There was an error processing the request. Typically:
    * The payload could not be found.
    * The payload could not be interpreted properly.
    * The payload was empty (zero metrics).
* Forbidden (**403**): Access denied. Typically:
    * The SID is missing.
    * The SID is invalid. 

The body of the response contains an additional **plain text message** providing more details on the status code.

### Python Example

Sample code in other languages below.

```python
import requests as req

sid = '0nl2l0x06HGV9Jog6cAbkg'

files = {"xls": open('../Sofia_Metrics_Template.xlsx', 'rb')}
data = {
	"sid": sid,
	"all_dates": True
}

url = 'http://sofia.conexo.vc/xls'

res = req.post(url, data=data, files=files)

print(res.status_code)
print(res.content)
```

## Data Upload API

Use this API to submit to Sofia only the relevant data fields corresponding to your metrics.

This is the best option if you already use a different system to track your company's progress and want to integrate directly with Sofia instead of using Excel as an intermediary step.

### Endpoint

```
http://sofia.conexo.vc/api
```

### Request

Perform a HTTP POST submission with the metrics as part its a JSON-encoded payload.

The JSON part must comply with the following format:

```js
{
	// sid part (string, optional)
	'sid': sid,
	// metrics part
	'metrics': {
		// date (date as string)
		'2020.01.01': {
			// metric id (int as string): metric value (float)
			'10': 3.605422555523507e-05,
			'11': 35.7,
			'12': 0.04579250190473688
		}
	}
}
```

There is no **all_dates** parameter. All the dates and metrics that you submit will be considered.

### Response

You will receive a [standard HTTP response status code](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) depending on the results of your action.

* Success (**200**): Successfully extracted at least one metric from the request.
* Error (**400**): There was an error processing the request. Typically:
    * The payload could not be found.
    * The payload could not be interpreted properly.
    * The payload was empty (zero metrics).
* Forbidden (**403**): Access denied. Typically:
    * The SID is missing.
    * The SID is invalid. 

The body of the response contains an additional **plain text message** providing more details on the status code.

### Python Example

Sample code in other languages below.

```python
import requests as req

sid = 'mySID'

data = {
	'sid': sid,
	'metrics': {
		'2020.01.01': {
			10: 3.605422555523507e-05
		}
	}
}

url = 'http://sofia.conexo.vc/api'

res = req.post(url, json=data)

print(res.status_code)
print(res.content)
```

## Sample Code

Available in each folder for the following languages:

* [Python](https://github.com/isaacdlp/sofia_api/tree/master/python)
* [PHP](https://github.com/isaacdlp/sofia_api/tree/master/php)
* [Ruby](https://github.com/isaacdlp/sofia_api/tree/master/ruby)
* [Java](https://github.com/isaacdlp/sofia_api/tree/master/java)
* [Node.js](https://github.com/isaacdlp/sofia_api/tree/master/node)
