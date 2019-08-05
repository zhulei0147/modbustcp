package com.zhulei.modbus;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusSlaveSet;
import com.serotonin.modbus4j.exception.ModbusInitException;

//springboot项目实现CommandLineRunner类和@Component注解，程序启动时就加载tcp slave
@Component
public class Slave implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		createSalve();
	}

	private void createSalve() {
		// 创建modbus工厂
		ModbusFactory modbusFactory = new ModbusFactory();
		// 创建TCP服务端
		final ModbusSlaveSet salve = modbusFactory.createTcpSlave(true);
		// 向过程影像区添加数据
		salve.addProcessImage(Register.getModscanProcessImage(1));
		// 获取寄存器
		salve.getProcessImage(1);
		try {
			salve.start();
		} catch (ModbusInitException e) {
			e.printStackTrace();
		}
	}

}
