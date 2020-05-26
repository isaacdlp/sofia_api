// Copyright (C) 2020 by Algonaut USA (http://algonaut.us).
// Please see the LICENSE file that should have been included as part of this package.
// Written by Isaac de la Pena <isaac@algonaut.us>

// npm install form-data

// node FileApi.js

var FormData = require('form-data');
const http = require('http')
const os = require('os')
const fs = require('fs')

const readStream = fs.createReadStream('../Sofia_Metrics_Template.xlsx');

const form = new FormData();
form.append('xls', readStream);
form.append('sid', 'mySID');
form.append('all_dates', true);

const options = {
  hostname: 'sofia.conexo.vc',
  port: 80,
  path: '/xls',
  method: 'POST',
  headers: form.getHeaders()
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

form.pipe(req);