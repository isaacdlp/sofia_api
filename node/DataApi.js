// Copyright (C) 2020 by Algonaut USA (http://algonaut.us).
// Please see the LICENSE file that should have been included as part of this package.
// Written by Isaac de la Pena <isaac@algonaut.us>

// node DataApi.js

const http = require('http')
const os = require('os')

const data = JSON.stringify({
    'sid': 'mySID',
    'metrics': {
	    '2020.01.01': {
		    '10': 3.605422555523507e-05,
			'11': 35.7,
			'12': 0.04579250190473688
		}
	}
})

const options = {
  hostname: 'sofia.conexo.vc',
  port: 80,
  path: '/api',
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
    'Content-Length': data.length
  }
}

const req = http.request(options, (res) => {
  console.log(`Status Code: ${res.statusCode}`)

  res.on('data', (d) => {
    process.stdout.write(d + os.EOL)
  })
})

req.on('error', (error) => {
  console.error(error)
})

req.write(data)
req.end()