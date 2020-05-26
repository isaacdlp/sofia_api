# Copyright (C) 2020 by Algonaut USA (http://algonaut.us).
# Please see the LICENSE file that should have been included as part of this package.
# Written by Isaac de la Pena <isaac@algonaut.us>

# python file_api.py

import requests as req

sid = 'mySID'

print('Sofia File API Sample Code')

files = {"xls": open('../Sofia_Metrics_Template.xlsx', "rb")}
data = {
	"sid": sid,
	"all_dates": True
}

url = 'http://sofia.conexo.vc/xls'
# or 'http://sofia.conexo.vc/xls/%s' % sid
# or 'http://sofia.conexo.vc/xls?sid=%s' % sid

res = req.post(url, data=data, files=files)

print(res.status_code)
print(res.reason)
print(res.content)