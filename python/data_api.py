# Copyright (C) 2020 by Algonaut USA (http://algonaut.us).
# Please see the LICENSE file that should have been included as part of this package.
# Written by Isaac de la Pena <isaac@algonaut.us>

# python data_api.py

import requests as req

sid = 'mySID'

print('Sofia Data API Sample Code')

data = {
	'sid': sid,
	'metrics': {
		'2020.01.01': {
			10: 3.605422555523507e-05,
			11: 35.7,
			12: 0.04579250190473688
		}
	}
}

url = 'http://sofia.conexo.vc/api'
# or 'http://sofia.conexo.vc/api/%s' % sid
# or 'http://sofia.conexo.vc/api?sid=%s' % sid

res = req.post(url, json=data)

print(res.status_code)
print(res.reason)
print(res.content)